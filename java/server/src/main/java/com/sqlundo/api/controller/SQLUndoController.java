package com.sqlundo.api.controller;

import com.sqlundo.api.model.QueryDTO;
import com.sqlundo.api.model.Script;
import com.sqlundo.api.service.SQLUndoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql-undo")
public class SQLUndoController {

    final private SQLUndoService sqlUndoService;

    public SQLUndoController(SQLUndoService sqlUndoService) {
        this.sqlUndoService = sqlUndoService;
    }

    @PostMapping
    public ResponseEntity<List<QueryDTO>> reverseScript(@RequestBody Script script) {
        List<QueryDTO> revertedQueries = sqlUndoService.undo(script);

        return ResponseEntity.ok(revertedQueries);
    }
}
