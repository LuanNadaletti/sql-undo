package com.sqlundo.api.service;

import com.sqlundo.SQLUndoManager;
import com.sqlundo.api.model.QueryDTO;
import com.sqlundo.api.model.Script;
import com.sqlundo.models.UndoResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SQLUndoService {

    public List<QueryDTO> undo(Script script) {
        List<UndoResult> results = SQLUndoManager.undo(script.script());

        return results.stream()
                .map(result -> new QueryDTO(result.getOriginal(), result.getReverted()))
                .toList();
    }
}
