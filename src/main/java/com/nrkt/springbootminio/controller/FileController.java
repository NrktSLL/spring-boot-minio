package com.nrkt.springbootminio.controller;

import com.nrkt.springbootminio.payload.FileResponse;
import com.nrkt.springbootminio.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/v1/files", produces = {"application/json", "application/xml", "application/hal+json"})
@Tag(name = "files", description = "File Service")
public class FileController {

    FileStorageService fileStorageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Upload a File")
    public ResponseEntity<FileResponse> fileUpload(@RequestPart("file") MultipartFile file) {
        FileResponse response = fileStorageService.addFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/view/{file}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "View a File")
    public ResponseEntity<InputStreamResource> viewFile(@PathVariable String file) {
        FileResponse source = fileStorageService.getFile(file);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(source.getContentType()))
                .contentLength(source.getFileSize())
                .header("Content-disposition", "attachment; filename=" + source.getFilename())
                .body(source.getStream());
    }

    @GetMapping("/download/{file}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Download a File")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String file) {
        FileResponse source = fileStorageService.getFile(file);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(source.getFileSize())
                .header("Content-disposition", "attachment; filename=" + source.getFilename())
                .body(source.getStream());
    }

    @DeleteMapping("/{file}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a File")
    public Object removeFile(@PathVariable String file) {
        fileStorageService.deleteFile(file);
        return ResponseEntity.noContent();
    }

    @GetMapping("/{file}/detail")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get File Detail")
    public ResponseEntity<FileResponse> getFileDetail(@PathVariable String file) {
        FileResponse response = fileStorageService.getFileDetails(file);
        return ResponseEntity.ok(response);
    }
}
