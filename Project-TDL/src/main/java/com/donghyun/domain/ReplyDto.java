package com.donghyun.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ReplyDto {

    private Integer idx;
    private String content;

    public ReplyDto (Reply reply) {

        this.idx = reply.getIdx();
        this.content = reply.getContent();

    }
}
