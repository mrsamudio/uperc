package co.edu.ucundinamarca.uperc.test.persistencia;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import static org.junit.Assert.assertFalse;

import java.io.FileInputStream;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudVisionTest {

//	@Autowired
//	private CloudVisionTemplate cloudVisionTemplate;

	private static String path = "src/test/resources/img/plates/";

	@Autowired
	private ResourceLoader resourceLoader;

//	@Test
	void extraerTexto() throws IOException {

//		String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(path.concat("2.webp")));
//		return "Text from image: " + textFromImage;

//		System.out.println("Text from image: " + textFromImage);

		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
				"/home/ingsamudio/Documentos/Universidad/ProyectoDeGrado/2021/google/uperc-310703-7b8bec4712b5.json"))
				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

		System.out.println("Buckets:");
		Page<Bucket> buckets = storage.list();
		for (Bucket bucket : buckets.iterateAll()) {
			System.out.println(bucket.toString());
		}

		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

			// The path to the image file to annotate
			String fileName = path + "wakeupcat.jpg";

			// Reads the image file into memory
			Path path = Paths.get(fileName);
			byte[] data = Files.readAllBytes(path);
			ByteString imgBytes = ByteString.copyFrom(data);

			// Builds the image annotation request
			List<AnnotateImageRequest> requests = new ArrayList<>();
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			// Performs label detection on the image file
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return;
				}

				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					annotation.getAllFields().forEach((k, v) -> System.out.format("%s : %s%n", k, v.toString()));
				}
			}
		}
	}

	@Test
	void detectText() throws IOException {
		// TODO(developer): Replace these variables before running the sample.
		String filePath = path + "3.webp";
		detectText(filePath);
		assertFalse(false);
	}

	// Detects text in the specified image.
	void detectText(String filePath) throws IOException {

//		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(
//				"/home/ingsamudio/Documentos/Universidad/ProyectoDeGrado/2021/google/uperc-310703-7b8bec4712b5.json"))
//				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

		List<AnnotateImageRequest> requests = new ArrayList<>();

		ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat1 = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
		Feature feat2 = Feature.newBuilder().setType(Feature.Type.OBJECT_LOCALIZATION).build();
		Feature feat3 = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
		Feature feat4 = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
		Feature feat5 = Feature.newBuilder().setType(Feature.Type.LOGO_DETECTION).build();

		AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
				.addFeatures(feat2)
				.addFeatures(feat4)
				.addFeatures(feat1).setImage(img).build();
		requests.add(request);

		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			// Perform the request
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return;
				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
					System.out.println("");
					System.out.format("Text: %s%n", annotation.getDescription());
					System.out.format("Position : %s%n", annotation.getBoundingPoly());
				}
			}

			// Display the results para object localization
			for (AnnotateImageResponse res : responses) {
				for (LocalizedObjectAnnotation entity : res.getLocalizedObjectAnnotationsList()) {
					System.out.format("Object name: %s%n", entity.getName());
					System.out.format("Confidence: %s%n", entity.getScore());
					System.out.format("Normalized Vertices:%n");
					entity.getBoundingPoly().getNormalizedVerticesList()
							.forEach(vertex -> System.out.format("- (%s, %s)%n", vertex.getX(), vertex.getY()));
				}
			}

			// para LABEL_DETECTION
			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return;
				}

				// For full list of available annotations, see http://g.co/cloud/vision/docs
				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					annotation.getAllFields().forEach((k, v) -> System.out.format("%s : %s%n", k, v.toString()));
				}
			}

		}

	}

}
