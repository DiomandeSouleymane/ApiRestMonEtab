package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.services.FileStorageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {
    private  final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        Map upload = cloudinary.uploader().upload(file.getBytes(), params1);
        System.out.println(upload);
        return upload.get("url").toString();
    }
}
