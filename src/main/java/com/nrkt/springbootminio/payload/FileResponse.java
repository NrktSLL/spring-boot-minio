package com.nrkt.springbootminio.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL, valueFilter = RepresentationModel.class)
public class FileResponse extends RepresentationModel<FileResponse> implements Serializable {
    String filename;
    String contentType;
    Long fileSize;
    Date createdTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(hidden = true)
    transient InputStreamResource stream;
}
