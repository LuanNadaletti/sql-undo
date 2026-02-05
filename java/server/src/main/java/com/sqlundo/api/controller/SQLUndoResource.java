package com.sqlundo.api.controller;

import com.sqlundo.api.model.QueryDTO;
import com.sqlundo.api.model.Script;
import com.sqlundo.api.service.SQLUndoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/sql-undo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SQLUndoResource {

    @Inject
    SQLUndoService sqlUndoService;

    @POST
    public List<QueryDTO> reverseScript(Script script) {
        return sqlUndoService.undo(script);
    }
}
