package com.sekpiseth.api.tag.web;

import com.sekpiseth.api.tag.TagService;
import com.sekpiseth.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@Slf4j
public class TagRestController {

    private final TagService tagService;

    @GetMapping
    public BaseRest<?> findAllTags(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit){
        var tags = tagService.findAll(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("All Tags Has Been Retrieved Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findTagById(@PathVariable("id") Integer id){
        var tags = tagService.findById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Tag Has Been Found Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

    @PostMapping
    public BaseRest<?> addNewTag(@RequestBody TagDto tagDto){
        var tags = tagService.addNew(tagDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("A Tag Has Been Created Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateTagById(@PathVariable("id") Integer id, @RequestBody TagDto tagDto){
        var tags = tagService.editById(id, tagDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("A Tag Has Been Updated Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteTagById(@PathVariable("id") Integer id){
        var tags = tagService.removeById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("A Tag Has Been Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

    @PatchMapping("/{id}")
    public BaseRest<?> deleteTagStatus(@PathVariable("id") Integer id, @RequestBody TagIsDeletedDto body){
        var tags = tagService.removeByStatus(id, body.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("A Tag Has Been Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .data(tags)
                .build();
    }

}
