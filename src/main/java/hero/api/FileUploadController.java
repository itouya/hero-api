package hero.api;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload2")
@CrossOrigin(origins="*")
public class FileUploadController {
	@Autowired
	HeroService heroService;
	
	@RequestMapping(method=RequestMethod.POST)
	String update(
			@RequestParam("account_image") MultipartFile multipartFile,
			@RequestParam("id") String id,
			@RequestParam("name") String name
			) {
		Path imagePath = Paths.get("/Users/yasuhiko/work/toh/src/assets/images/");
		Path imageFile = Paths.get(imagePath + "/" + id + ".png");
		try (OutputStream os = Files.newOutputStream(imageFile, StandardOpenOption.CREATE)) {
			byte[] bytes = multipartFile.getBytes();
			os.write(bytes);
		} catch(IOException ex) {
			System.err.println(ex);
		}
		Hero hero = new Hero();
		hero.setId(Integer.parseInt(id));
		hero.setName(name);
		hero.setAccount_image("/assets/images/" + id + ".jpg");
		heroService.update(hero);
		System.out.println("upload " + name + "'s file.");
		return null;
	}
}
