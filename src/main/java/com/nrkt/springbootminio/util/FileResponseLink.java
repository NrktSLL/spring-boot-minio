package com.nrkt.springbootminio.util;

import com.nrkt.springbootminio.controller.FileController;
import com.nrkt.springbootminio.payload.FileResponse;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FileResponseLink implements LinkUtils<FileResponse> {
    public FileResponse addOperationWithLink(FileResponse response) {
        Link[] links = new Link[]{
                linkTo(methodOn(FileController.class).getFileDetail(response.getFilename()))
                        .withRel("file")
                        .withType("GET")
                        .withDeprecation("File Detail"),
                linkTo(methodOn(FileController.class).viewFile(response.getFilename()))
                        .withRel("file")
                        .withType("GET")
                        .withDeprecation("View File"),
                linkTo(methodOn(FileController.class).downloadFile(response.getFilename()))
                        .withRel("file")
                        .withType("GET")
                        .withDeprecation("Download File"),
                linkTo(methodOn(FileController.class).removeFile(response.getFilename()))
                        .withRel("file")
                        .withType("DELETE")
                        .withDeprecation("Delete File")
        };
        return response.add(links);
    }

    public FileResponse getOperationWithLink(FileResponse response) {
        Link[] links = new Link[]{
                linkTo(methodOn(FileController.class).fileUpload(null))
                        .withRel("file")
                        .withType("POST")
                        .withDeprecation("Add File"),
                linkTo(methodOn(FileController.class).viewFile(response.getFilename()))
                        .withRel("file")
                        .withType("GET")
                        .withDeprecation("View File"),
                linkTo(methodOn(FileController.class).downloadFile(response.getFilename()))
                        .withRel("file")
                        .withType("GET")
                        .withDeprecation("Download File"),
                linkTo(methodOn(FileController.class).removeFile(response.getFilename()))
                        .withRel("file")
                        .withType("DELETE")
                        .withDeprecation("Delete File")
        };
        return response.add(links);
    }
}
