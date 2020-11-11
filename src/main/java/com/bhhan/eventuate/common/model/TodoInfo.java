package com.bhhan.eventuate.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class TodoInfo {
    private String title;
    private boolean completed;
    private int order;
}
