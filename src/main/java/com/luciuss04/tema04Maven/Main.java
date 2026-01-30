package com.luciuss04.tema04Maven;

import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<String> lines = new ArrayList<>();

        String texto = "Josep Perez";
        String banner = FigletFont.convertOneLine(texto);

        lines.addAll(Arrays.asList(banner.split("\n")));
        lines.add("");

        lines.add("CURRICULUM VITAE");
        lines.add("Nombre: Josep Perez");
        lines.add("Estudiante de Desarrollo de Aplicaciones Multiplataforma");
        lines.add("Conocimientos en Java y programacion orientada a objetos");
        lines.add("Uso de Maven para gestion de dependencias");
        lines.add("Experiencia con control de versiones Git y GitHub");
        lines.add("Manejo de IntelliJ IDEA como IDE principal");
        lines.add("Conocimientos basicos de bases de datos");
        lines.add("Capacidad de trabajo en equipo");
        lines.add("Motivacion por el aprendizaje continuo");
        lines.add("Email: luciuss0444@gmail.com");

        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.setCursorPosition(null);

        int height = screen.getTerminalSize().getRows();
        int yOffset = height;
        int delayMs = 100;

        while (yOffset > -lines.size()) {
            drawFrame(screen, lines, yOffset);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ignored) {}
            yOffset--;
        }

        screen.stopScreen();
    }

    private static void drawFrame(Screen screen, List<String> lines, int yOffset)
            throws IOException {

        TerminalSize size = screen.getTerminalSize();
        int width = size.getColumns();
        int height = size.getRows();

        screen.clear();
        TextGraphics tg = screen.newTextGraphics();

        for (int i = 0; i < lines.size(); i++) {
            int y = yOffset + i;
            if (y < 0 || y >= height) continue;

            String line = lines.get(i);
            int x = Math.max(0, (width - line.length()) / 2);
            if (x >= width) continue;

            String visible = (line.length() > width)
                    ? line.substring(0, width)
                    : line;

            tg.putString(x, y, visible);
        }

        screen.refresh();
    }

}
