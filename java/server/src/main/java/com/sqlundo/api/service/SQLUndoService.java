package com.sqlundo.api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sqlundo.api.model.QueryDTO;
import com.sqlundo.api.model.Script;
import com.sqlundo.functional.SQLUndoManager;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SQLUndoService {

    public List<QueryDTO> undo(Script script) {
        return SQLUndoManager.undo(script.script())
                .stream()
                .map(q -> new QueryDTO(q.original(), q.reverted()))
                .collect(Collectors.toList());
    }
}
