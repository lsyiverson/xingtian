package com.lsyiverson.xingtian.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringUtilTest{
    @Test
    public void should_generate_address_when_give_province_and_city() throws Exception {
        //given
        String province = "四川";
        String city = "成都";

        //when
        String address = StringUtil.formatAddress(province, city);

        //then
        assertThat(address).isEqualTo("四川 成都");
    }
}