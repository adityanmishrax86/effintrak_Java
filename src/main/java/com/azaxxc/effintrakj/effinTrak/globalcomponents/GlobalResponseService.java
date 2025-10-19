package com.azaxxc.effintrakj.effinTrak.globalcomponents;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GlobalResponseService {
    public ResponseEntity<Object> success(Object data, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Object> success(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", message);
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Object> error(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", message);
        body.put("data", null);
        return ResponseEntity.status(status).body(body);
    }
}

