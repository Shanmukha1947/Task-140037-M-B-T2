import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FacePreprocessor {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String inputImagePath = "path/to/input/image.jpg";
        String outputImagePath = "path/to/output/image.jpg";

        Mat inputImage = Imgcodecs.imread(inputImagePath);
        if (inputImage.empty()) {
            System.out.println("Error: Cannot load image");
            return;
        }

        // Resize the image to a fixed size (224x224 is commonly used for facial recognition)
        Mat resizedImage = new Mat();
        Imgproc.resize(inputImage, resizedImage, new org.opencv.core.Size(224, 224));

        // Normalize the image to mean = 0 and standard deviation = 1
        Core.subtract(resizedImage, new Scalar(104, 117, 123), resizedImage);
        Core.divide(resizedImage, new Scalar(58, 58, 58), resizedImage);

        Imgcodecs.imwrite(outputImagePath, resizedImage);
        System.out.println("Image preprocessing completed!");
    }
}
