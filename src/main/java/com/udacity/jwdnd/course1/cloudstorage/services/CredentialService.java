package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private CredentialMapper credentialsMapper;

    private EncryptionService encryptionService;
    public CredentialService(CredentialMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public List<CredentialModel> getAll(Integer userId) {
        List<CredentialModel> credentials = credentialsMapper.getAllByUserId(userId);
        return credentials.stream().map(e -> {
            CredentialModel c = new CredentialModel();
            c.setCredentialId(e.getCredentialId());
            c.setUrl(e.getUrl());
            c.setUsername(e.getUsername());
            c.setPassword(e.getPassword());
            c.setRawPassword(encryptionService.decryptValue(e.getPassword(), e.getKey()));
            return c;
        }).collect(Collectors.toList());
    }

    @Transactional
    public int save(CredentialModel credentials) {
        if (!ObjectUtils.isEmpty(credentials.getCredentialId())) {
            CredentialModel credentialModel = credentialsMapper.getById(credentials.getCredentialId(), credentials.getUserId());
            if (!credentials.getUrl().equals(credentialModel.getUrl()) || !credentials.getUsername().equals(credentialModel.getUsername())) {
                if (credentialsMapper.existsByUrl(credentials.getUrl(), credentials.getUsername()) != 0) {
                    return 0;
                }
            }
            credentialModel.setUrl(credentials.getUrl());
            credentialModel.setUsername(credentials.getUsername());
            credentialModel.setPassword(encryptionService.encryptValue(credentials.getPassword(), credentialModel.getKey()));
            return credentialsMapper.update(credentialModel);
        }

        if (credentialsMapper.existsByUrl(credentials.getUrl(), credentials.getUsername())  != 0) {
            return 0;
        }
        byte[] key = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(), encodedKey));
        credentials.setUserId(credentials.getUserId());

        return credentialsMapper.insert(credentials);
    }

    @Transactional
    public int deleteCredential(Integer credentialId, Integer userId) {
        credentialsMapper.getById(credentialId, userId);
        return credentialsMapper.deleteById(credentialId);
    }

}
