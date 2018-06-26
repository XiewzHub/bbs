package com.hnie.forum.service.impl;

import com.hnie.forum.mapper.SequenceMapper;
import com.hnie.forum.service.SequenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xiewz on 2018/6/26.
 */
@Service("sequenceService")
@Transactional
public class SequenceServiceImp implements SequenceService {
    @Resource(name = "sequenceMapper")
    private SequenceMapper sequenceMapper;

    @Override
    public String getNextSeq(String seqName) {
        return sequenceMapper.findNextSeq(seqName);
    }
}
