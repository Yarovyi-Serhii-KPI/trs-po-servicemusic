package com.musicstreamer.servicetags.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicstreamer.servicetags.service.impl.TagServiceImpl;
import com.musicstreamer.servicetags.repo.model.Tag;
import com.musicstreamer.servicetags.api.dto.TagDto;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/tags")
@RestController
public class TagController {
    private final TagServiceImpl tagServiceImpl;

    @GetMapping
    public ResponseEntity<List<Tag>> index(){
        final List<Tag> tags = tagServiceImpl.fetchAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> showById(@PathVariable long id) {
        try {
            final Tag tag = tagServiceImpl.fetchTagById(id);

            return ResponseEntity.ok(tag);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TagDto tagDto){
        final String name = tagDto.name();
        final long song = tagDto.song();
        final long tagId = tagServiceImpl.createTag(name,song);
        final String tagURI = String.format("/songs/%d", tagId);

        return ResponseEntity.created(URI.create(tagURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody TagDto tagDto) {
        final String name = tagDto.name();

        try {
            tagServiceImpl.updateTag(id, name);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        tagServiceImpl.deleteTag(id);

        return ResponseEntity.noContent().build();
    }
}
