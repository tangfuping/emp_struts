package action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import utils.ImageUtil;

public class CreateValidateCodeAction extends BaseAction {
	// output
	private InputStream imageStream;

	public String execute() {
		// 1.调用工具类，生成验证码及图片
		Map<String, BufferedImage> imageMap = ImageUtil.createImage();
		// 2.从imageMap中取到验证码，并放入session
		String imageCode = imageMap.keySet().iterator().next();
		session.put("imageCode", imageCode);
		// 3.从imageMap中取到图片，转为输入流
		BufferedImage image = imageMap.get(imageCode);
		try {
			imageStream = ImageUtil.getInputStream(image);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

}
