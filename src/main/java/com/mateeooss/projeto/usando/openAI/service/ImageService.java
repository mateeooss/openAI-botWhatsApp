package com.mateeooss.projeto.usando.openAI.service;

import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageService {
    public static String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
//        javax.imageio.ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        // Converte a imagem para Base64
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
