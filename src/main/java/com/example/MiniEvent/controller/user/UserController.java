package com.example.MiniEvent.controller.user;

import com.example.MiniEvent.DTO.EventDTO;
import com.example.MiniEvent.model.Event;
import com.example.MiniEvent.response.ResponseObject;
import com.example.MiniEvent.service.event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.version}/users")
@Slf4j
public class UserController {

    private final EventService eventService;

    @PostMapping(value = "/event", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvent(
            @Valid @RequestPart("event") EventDTO eventDTO,
            @RequestPart("image") MultipartFile image ) throws Exception {
        Event event = eventService.createEvent(eventDTO, image);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message("Event create successfully")
                        .data(event)
                        .build()
        );
    }
}
