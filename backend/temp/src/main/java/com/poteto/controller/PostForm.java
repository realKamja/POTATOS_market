package com.poteto.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PostForm {
	 @NotBlank(message = "������ �Է����ּ���.")
	    private String title;

	    private String content;

}
