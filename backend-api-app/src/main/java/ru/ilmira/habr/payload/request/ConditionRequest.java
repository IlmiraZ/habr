package ru.ilmira.habr.payload.request;

import lombok.*;
import ru.ilmira.habr.persist.model.EUserCondition;
import ru.ilmira.habr.validation.ConditionTypeSubset;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConditionRequest {

    @NotNull
    @Min(value = 1)
    private Long userId;

    @NotNull
    @ConditionTypeSubset(anyOf = {EUserCondition.ACTIVE, EUserCondition.NOT_ACTIVE, EUserCondition.BANNED})
    private EUserCondition condition;
}
