package com.example.recode.service;

import com.example.recode.domain.Folder;
import com.example.recode.domain.User;
import com.example.recode.dto.folder.FolderAddRequestDto;
import com.example.recode.dto.folder.FolderListDto;
import com.example.recode.repository.FolderRepository;
import com.example.recode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final UserRepository userRepository;
    private final FolderRepository folderRepository;

    @Transactional(readOnly = true)
    public Folder getFolder(Long folderId) {
        return folderRepository.findById(folderId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 폴더입니다."));
    }

    @Transactional(readOnly = true)
    public List<FolderListDto> getFolderList(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자 정보입니다."));
        List<Folder> folders = folderRepository.findAllByUser(user);
        return folders.stream()
                .map(folder -> FolderListDto.builder()
                        .id(folder.getId())
                        .name(folder.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void addFolder(FolderAddRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자 정보입니다."));
        Folder folder = dto.toEntity(user);
        folderRepository.save(folder);
    }

    @Transactional
    public void updateFolderName(Long folderId, String folderName) {
        Folder folder = getFolder(folderId);
        folder.updateFolderName(folderName);
    }

    @Transactional
    public void deleteFolder(Long folderId) {
        folderRepository.delete(getFolder(folderId));
    }
}
