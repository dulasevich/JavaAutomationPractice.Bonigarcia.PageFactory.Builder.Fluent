package builder;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Chapter {
    String chapterName;
    List<String> links;
}
