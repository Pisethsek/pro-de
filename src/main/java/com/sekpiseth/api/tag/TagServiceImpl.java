package com.sekpiseth.api.tag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sekpiseth.api.tag.web.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private final TagMapStruct tagMapStruct;

    @Override
    public PageInfo<TagDto> findAll(Integer page, Integer limit) {
        PageInfo<Tag> tags = PageHelper.startPage(page, limit).doSelectPageInfo(tagRepository::select);
        return tagMapStruct.toPageInfoTagDto(tags);
    }

    @Override
    public TagDto findById(Integer id) {
        Tag tag = tagRepository.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Tag With %s Is Not Found!!!", id)));
        return tagMapStruct.toTag(tag);
    }

    @Override
    public TagDto addNew(TagDto tagDto) {
        Tag tag = tagMapStruct.fromTagDto(tagDto);
        tag.setCreated(Date.valueOf(LocalDate.now()));
        tag.setStatus(false);
        tagRepository.insert(tag);
        return this.findById(tag.getId());
    }

    @Override
    public TagDto editById(Integer id, TagDto tagDto) {
        if (tagRepository.isExists(id)){
            Tag tag = tagMapStruct.fromTagDto(tagDto);
            tag.setId(id);
            tagRepository.update(tag);
            return this.findById(tag.getId());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Tag With %s Is Not Found!!!", id));
    }

    @Override
    public Integer removeById(Integer id) {
        Boolean founded = tagRepository.isExists(id);
        if (founded){
            tagRepository.delete(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Tag With %s Is Not Found!!!", id));
    }

    @Override
    public Integer removeByStatus(Integer id, Boolean status) {
        Boolean founded = tagRepository.isExists(id);
        if (founded){
            tagRepository.deleteByStatus(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Tag With %s Is Not Found!!!", id));
    }

}
