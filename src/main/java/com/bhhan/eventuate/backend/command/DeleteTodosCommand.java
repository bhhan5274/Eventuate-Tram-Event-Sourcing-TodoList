package com.bhhan.eventuate.backend.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class DeleteTodosCommand implements TodoCommand{
    private List<String> ids;

    public DeleteTodosCommand(List<String> ids) {
        this.ids = ids;
    }
}
