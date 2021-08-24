package co.edu.ucundinamarca.upercth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.Label;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;
import software.amazon.awssdk.services.rekognition.model.S3Object;
import software.amazon.awssdk.services.rekognition.model.TextDetection;

public class Reconocimiento {

	private final Region region = Region.US_WEST_2;

	public static String path = "src/main/resources/img/plates/";

	private String imagen;

	public Reconocimiento() {
		// TODO Auto-generated constructor stub

	}

	public String ejecutar(String bucket) {
		RekognitionClient rekClient = RekognitionClient.builder().region(region).build();

//		detectImageLabels(rekClient, getImagen());

//		getLabelsfromImage(rekClient, bucket, getImagen());
//		detectTextLabels(rekClient, bucket, getImagen());
		String placa = detectarPlaca(rekClient, bucket, getImagen());
		rekClient.close();
		
		return placa;
	}

	public static void detectImageLabels(RekognitionClient rekClient, String sourceImage) {

		try {
			InputStream sourceStream = new FileInputStream(sourceImage);
			SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

			// Create an Image object for the source image.
			Image souImage = Image.builder().bytes(sourceBytes).build();

			DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder().image(souImage).maxLabels(10)
					.build();

			DetectLabelsResponse labelsResponse = rekClient.detectLabels(detectLabelsRequest);
			List<Label> labels = labelsResponse.labels();

			System.out.println("Detected labels for the given photo");
			for (Label label : labels) {
				System.out.println(label.name() + ": " + label.confidence().toString());
			}

		} catch (RekognitionException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getLabelsfromImage(RekognitionClient rekClient, String bucket, String image) {

		try {
			S3Object s3Object = S3Object.builder().bucket(bucket).name(image).build();

			Image myImage = Image.builder().s3Object(s3Object).build();

			DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder().image(myImage).maxLabels(10)
					.build();

			DetectLabelsResponse labelsResponse = rekClient.detectLabels(detectLabelsRequest);
			List<Label> labels = labelsResponse.labels();

			System.out.println("Detected labels for the given photo");
			for (Label label : labels) {
				System.out.println(label.name() + ": " + label.confidence().toString());
			}
			
			System.out.println("terminó");

		} catch (RekognitionException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void detectTextLabels(RekognitionClient rekClient, String bucket, String image) {

		try {

//			InputStream sourceStream = new FileInputStream(image);
//			SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

			// Create an Image object for the source image
//			Image souImage = Image.builder().bytes(sourceBytes).build();
			
			S3Object s3Object = S3Object.builder().bucket(bucket).name(image).build();
			Image myImage = Image.builder().s3Object(s3Object).build();

			DetectTextRequest textRequest = DetectTextRequest.builder().image(myImage).build();

			DetectTextResponse textResponse = rekClient.detectText(textRequest);
			List<TextDetection> textCollection = textResponse.textDetections();

			System.out.println("Detected lines and words");
			for (TextDetection text : textCollection) {
				System.out.println("Detected: " + text.detectedText());
				System.out.println("Confidence: " + text.confidence().toString());
				System.out.println("Id : " + text.id());
				System.out.println("Parent Id: " + text.parentId());
				System.out.println("Type: " + text.type());
				System.out.println();
			}
			
			System.out.println("terminó");

		} catch (RekognitionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Obtiene la placa detectada de la imagen tomada de la cámara
	 * 
	 * @param rekClient
	 * @param bucket
	 * @param image
	 */
	public String detectarPlaca(RekognitionClient rekClient, String bucket, String image) {

		try {

			S3Object s3Object = S3Object.builder().bucket(bucket).name(image).build();
			Image myImage = Image.builder().s3Object(s3Object).build();

			DetectTextRequest textRequest = DetectTextRequest.builder().image(myImage).build();

			DetectTextResponse textResponse = rekClient.detectText(textRequest);
			List<TextDetection> textCollection = textResponse.textDetections();

			System.out.println("Detected lines and words");
			for (TextDetection text : textCollection) {
				
				if(text.detectedText().contains("-"))
					return text.detectedText();
				else return "";
			}
			
//			System.out.println("terminó");
			
			
			return "";

		} catch (RekognitionException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
