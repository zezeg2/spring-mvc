package uploads;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadDTO {
    String name;
    String description;
    MultipartFile file1;
    MultipartFile file2;
}
