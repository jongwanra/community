package com.community.global.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.community.global.enums.ErrorCode;

@Target({METHOD, ANNOTATION_TYPE, FIELD})
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiResponseCode {
	ErrorCode value();

}
