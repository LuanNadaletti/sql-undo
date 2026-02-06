package com.sqlundo.api.service;

import com.sqlundo.SQLUndoManager;
import com.sqlundo.api.model.QueryDTO;
import com.sqlundo.api.model.Script;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SQLUndoService {

    public List<QueryDTO> undo(Script script) {
        return SQLUndoManager.undo(script.script())
                .stream()
                .map(q -> new QueryDTO(q.original(), q.reverted()))
                .collect(Collectors.toList());
    }
}
