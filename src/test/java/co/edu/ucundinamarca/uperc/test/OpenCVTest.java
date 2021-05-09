package co.edu.ucundinamarca.uperc.test;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import nu.pattern.OpenCV;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OpenCVTest {
	
	private static String path = "src/test/resources/img/plates";
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void opencvInicial() {
		
		OpenCV.loadShared();
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
//		try {
//			File file = ResourceUtils.getFile(this.getClass().getResource("/some_file.txt"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		Mat imagen = cargarImagen(path + "/2.webp");
		
		Imgproc.cvtColor(imagen, imagen, Imgproc.COLOR_BGR2GRAY, 0);
	    Mat originalFrameGrayScale = imagen.clone();

	    Mat morph = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 3));
	    Imgproc.morphologyEx(imagen, imagen, Imgproc.MORPH_TOPHAT, morph);
	    Imgproc.Sobel(imagen, imagen, -1, 2, 0);
	    Imgproc.GaussianBlur(imagen, imagen, new Size(5,5), 3,3);
	    Imgproc.morphologyEx(imagen, imagen, Imgproc.MORPH_CLOSE, morph);
	    Imgproc.threshold(imagen, imagen, 200, 255, Imgproc.THRESH_OTSU);
//	    Vector<Rect> rectangles = detectionContour(imagen);
		saveImage(imagen, path + "/procesada.webp");
		
		assertFalse(true);
	}
	
	
	public static Mat cargarImagen(String imagePath) {
//	    Imgcodecs imageCodecs = new Imgcodecs();
	    return Imgcodecs.imread(imagePath);
	}
	
	public static void saveImage(Mat imageMatrix, String targetPath) {
	    Imgcodecs imgcodecs = new Imgcodecs();
	    imgcodecs.imwrite(targetPath, imageMatrix);
	}
	
	public static void saveImage(Mat imageMatrix) {
//		Imgcodecs imgcodecs = new Imgcodecs();
		Imgcodecs.imwrite(path, imageMatrix);
	}
	
	
	

}
