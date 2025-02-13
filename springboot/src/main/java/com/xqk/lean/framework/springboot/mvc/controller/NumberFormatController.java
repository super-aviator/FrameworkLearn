package com.xqk.lean.framework.springboot.mvc.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author 熊乾坤
 */
@RestController
@RequestMapping("/numberFormat")
public class NumberFormatController {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.#");

    @GetMapping("/double")
    public HttpEntity<ContainNumber> getNumber() {
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return new HttpEntity<>(new ContainNumber(decimalFormat.format(10.255)));
    }

    @Data
    @AllArgsConstructor
    private static class ContainNumber {

        private Double number;

        private String numberStr;

        ContainNumber(String str) {
            this.numberStr = str;
        }
    }
}
