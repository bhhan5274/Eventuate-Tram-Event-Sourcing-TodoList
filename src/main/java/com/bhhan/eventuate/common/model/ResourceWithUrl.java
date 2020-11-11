package com.bhhan.eventuate.common.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@XmlRootElement
public class ResourceWithUrl<T> {
    private String id;
    @JsonUnwrapped
    private T content;

    private String url;

    public ResourceWithUrl() {
    }

    public ResourceWithUrl(T content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAnyElement
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
