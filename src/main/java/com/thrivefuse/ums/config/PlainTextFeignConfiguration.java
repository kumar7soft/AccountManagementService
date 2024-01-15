package com.thrivefuse.ums.config;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;

@Configuration
public class PlainTextFeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new StringDecoder();
    }

    static class StringDecoder implements Decoder {
        @Override
        public Object decode(Response response, Type type) throws IOException, FeignException {
            if (response.body() == null) return null;
            if (String.class.equals(type)) {
                return Util.toString(response.body().asReader(Util.UTF_8));
            }
            throw new DecodeException(response.status(), "Type " + type + " not supported", response.request(), null);
        }
    }
}
