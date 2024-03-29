package ru.ilmira.habr.service;

import lombok.RequiredArgsConstructor;
import ru.ilmira.habr.exception.BadRequestException;
import ru.ilmira.habr.exception.ForbiddenException;
import ru.ilmira.habr.exception.UserNotFoundException;
import ru.ilmira.habr.payload.request.UpdateUserInfoRequest;
import ru.ilmira.habr.payload.response.MessageResponse;
import ru.ilmira.habr.persist.model.ERole;
import ru.ilmira.habr.persist.model.EUserCondition;
import ru.ilmira.habr.persist.model.Role;
import ru.ilmira.habr.persist.model.User;
import ru.ilmira.habr.persist.repository.RoleRepository;
import ru.ilmira.habr.persist.repository.UserRepository;
import ru.ilmira.habr.persist.specification.UserSpecification;
import ru.ilmira.habr.service.dto.UserDto;
import ru.ilmira.habr.service.dto.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.ilmira.habr.util.SpecificationUtils.combineSpec;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Value("${app.defaultSizePerPage.users}")
    private int defaultSizePerPage;

    @Value("${app.defaultSortField.users}")
    private String defaultSortField;

    @Value("${app.defaultSortDirection.users}")
    private String defaultSortDirection;

    @Override
    public Page<UserDto> findAll(
            Optional<String> username,
            Optional<String> firstName,
            Optional<String> lastName,
            Optional<String> condition,
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortField,
            Optional<Direction> direction) {
        Specification<User> spec = null;

        if (username.isPresent() && !username.get().isBlank()) {
            spec = Specification.where(UserSpecification.usernameLike(username.get()));
        }
        if (firstName.isPresent() && !firstName.get().isBlank()) {
            spec = combineSpec(spec, UserSpecification.firstNameLike(firstName.get()));
        }
        if (lastName.isPresent() && !lastName.get().isBlank()) {
            spec = combineSpec(spec, UserSpecification.lastNameLike(lastName.get()));
        }

        try {
            if (condition.isPresent()) {
                spec = combineSpec(spec,
                        UserSpecification
                                .condition(EUserCondition.valueOf(condition.get().toUpperCase())));
            }
        } catch (IllegalArgumentException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        spec = combineSpec(spec, Specification.where(null));

        String sortBy;
        if (sortField.isPresent() && !sortField.get().isEmpty()) {
            sortBy = sortField.get();
        } else {
            sortBy = defaultSortField;
        }
        int pageValue = page.orElse(1) - 1;
        int sizeValue = size.orElse(defaultSizePerPage);
        Direction directionValue = direction.orElse(Direction.valueOf(defaultSortDirection));

        return userRepository.findAll(spec,
                        PageRequest.of(
                                pageValue,
                                sizeValue,
                                Sort.by(directionValue, sortBy)))
                .map(userMapper::fromUser);
    }

    @Override
    public Optional<UserDto> findById(long userId) {
        return userRepository.findById(userId).map(userMapper::fromUser);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::fromUser);
    }

    @Override
    public Optional<UserDto> findByUsername(String username, EUserCondition condition) {
        Specification<User> spec = Specification
                .where(UserSpecification.username(username))
                .and(UserSpecification.condition(condition))
                .and(UserSpecification.fetchRoles());

        return userRepository.findOne(spec).map(userMapper::fromUser);
    }

    @Transactional
    @Override
    public MessageResponse deleteById(Long userId) {

        User user = getUserByIdAndCheckRoles(userId);

        user.setCondition(EUserCondition.DELETED);
        userRepository.save(user);

        return new MessageResponse(
                "User [" + user.getUsername() + "] condition successfully changed to " +
                        EUserCondition.DELETED);
    }

    @Transactional
    @Override
    public UserDto update(String username, UpdateUserInfoRequest request) {
        User fetchedUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User by username = " + username + " not found."));

        fetchedUser.setFirstName(request.getFirstName());
        fetchedUser.setLastName(request.getLastName());
        fetchedUser.setAboutMe(request.getAboutMe());
        fetchedUser.setBirthday(request.getBirthday());

        User updatedUser = userRepository.save(fetchedUser);

        return userMapper.fromUser(updatedUser);
    }

    @Transactional
    @Override
    public MessageResponse promote(Long userId, Set<String> promotedRoles) {

        // verifying list of presented roles
        Set<ERole> eRoleSet;
        try {
            eRoleSet = promotedRoles.stream().map(name ->
                            ERole.valueOf(name.toUpperCase()))
                    .collect(Collectors.toSet());
        } catch (IllegalArgumentException exception) {
            throw new BadRequestException("Check list of provided roles.");
        }

        User user = getUserByIdAndCheckRoles(userId);

        Set<Role> roles = getRolesFromDb(eRoleSet);

        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponse("User [" + user.getUsername() + "] promoted.");
    }

    @Transactional
    @Override
    public MessageResponse condition(Long userId, EUserCondition condition) {
        User user = getUserByIdAndCheckRoles(userId);
        EUserCondition prevCondition = user.getCondition();

        user.setCondition(condition);

        userRepository.save(user);
        return new MessageResponse("Successfully changed condition from " +
                "[" + prevCondition + "] " +
                "to [" + condition + "] " +
                "for User [" + user.getUsername() + "].");
    }

    private Set<Role> getRolesFromDb(Set<ERole> roles) {
        return roleRepository.findAll().stream()
                .filter(role -> roles.contains(role.getName()))
                .collect(Collectors.toSet());
    }

    private Set<ERole> getERolesFromAuth(Authentication auth) {
        return auth.getAuthorities().stream()
                .map(authority -> {
                    String role = authority.getAuthority().replaceFirst("SCOPE_", "");
                    return ERole.valueOf(role);
                })
                .collect(Collectors.toSet());
    }

    private User getUserByIdAndCheckRoles(Long userId) {
        // get request owner (manager) data
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String managerUsername = auth.getName();
        Set<ERole> managerRoles = getERolesFromAuth(auth);

        // get user data
        Specification<User> spec = Specification
                .where(UserSpecification.id(userId))
                .and(UserSpecification.fetchRoles());

        User user = userRepository.findOne(spec)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id = " + userId + " not found."));

        // Throw exception if user & manager it's a same person
        if (managerUsername.equals(user.getUsername())) {
            throw new ForbiddenException("Not allowed to edit yourself.");
        }

        Set<ERole> userRoles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        boolean isSimpleUser = userRoles.size() == 1 && userRoles.contains(ERole.ROLE_USER);

        // TODO verify this logic
        if (!isSimpleUser) {
            int userPriority = userRoles.stream().mapToInt(ERole::ordinal).sum();
            int managerPriority = managerRoles.stream().mapToInt(ERole::ordinal).sum();

            if (managerPriority <= userPriority) {
                throw new ForbiddenException("Insufficient rights to edit user with roles " + userRoles);
            }
        }
        return user;
    }
}
