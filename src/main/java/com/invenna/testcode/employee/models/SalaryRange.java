package com.invenna.testcode.employee.models;

import java.math.BigDecimal;
import lombok.Value;

public record SalaryRange(BigDecimal from, BigDecimal to) {

}
