package org.iesvdm.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FechaFiltro {
    private LocalDate hoy = LocalDate.now();

    // Convierte Date a LocalDate
    private LocalDate convertirDateALocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Determina si la fecha dada (Date) está en el último trimestre
    public boolean estaEnUltimoTrimestre(Date fecha) {
        LocalDate localFecha = convertirDateALocalDate(fecha);
        LocalDate inicioUltimoTrimestre = obtenerInicioUltimoTrimestre();
        LocalDate finUltimoTrimestre = inicioUltimoTrimestre.plusMonths(3).minusDays(1);

        System.out.println("Fecha del pedido: " + localFecha);
        System.out.println("Inicio del último trimestre: " + inicioUltimoTrimestre);
        System.out.println("Fin del último trimestre: " + finUltimoTrimestre);

        // Verificamos las comparaciones de fechas
        boolean estaEnTrimestre = !localFecha.isBefore(inicioUltimoTrimestre) && !localFecha.isAfter(finUltimoTrimestre);
        System.out.println("Está en el último trimestre: " + estaEnTrimestre);

        return estaEnTrimestre;
    }

    // Determina si la fecha dada (Date) está en el último semestre
    public boolean estaEnUltimoSemestre(Date fecha) {
        LocalDate localFecha = convertirDateALocalDate(fecha);
        LocalDate inicioUltimoSemestre = obtenerInicioUltimoSemestre();
        return !localFecha.isBefore(inicioUltimoSemestre) && !localFecha.isAfter(hoy);
    }

    // Determina si la fecha dada (Date) está en el último año
    public boolean estaEnUltimoAnio(Date fecha) {
        LocalDate localFecha = convertirDateALocalDate(fecha);
        LocalDate inicioUltimoAnio = hoy.minusYears(1).plusDays(1);
        return !localFecha.isBefore(inicioUltimoAnio) && !localFecha.isAfter(hoy);
    }

    // Determina si la fecha dada (Date) está en el último lustro
    public boolean estaEnUltimoLustro(Date fecha) {
        LocalDate localFecha = convertirDateALocalDate(fecha);
        LocalDate inicioUltimoLustro = hoy.minusYears(5).plusDays(1);
        return !localFecha.isBefore(inicioUltimoLustro) && !localFecha.isAfter(hoy);
    }

    // Calcula el inicio del último trimestre
    private LocalDate obtenerInicioUltimoTrimestre() {
        int mes = hoy.getMonthValue();

        // Si estamos en los últimos meses del año (octubre a diciembre), el último trimestre es este año.
        if (mes >= 10) {
            return LocalDate.of(hoy.getYear(), Month.OCTOBER, 1);
        }
        // Si estamos entre julio y septiembre, el último trimestre es julio - septiembre de este año.
        else if (mes >= 7) {
            return LocalDate.of(hoy.getYear(), Month.JULY, 1);
        }
        // Si estamos entre abril y junio, el último trimestre es abril - junio de este año.
        else if (mes >= 4) {
            return LocalDate.of(hoy.getYear(), Month.APRIL, 1);
        }
        // Si estamos en el primer trimestre (enero a marzo), el último trimestre es el 1 de octubre del año anterior.
        else {
            return LocalDate.of(hoy.getYear() - 1, Month.OCTOBER, 1);  // Cambié el año a `hoy.getYear() - 1`
        }
    }

    // Calcula el inicio del último semestre
    private LocalDate obtenerInicioUltimoSemestre() {
        int mes = hoy.getMonthValue();

        // Si estamos en el segundo semestre (julio a diciembre), el último semestre es de este año.
        if (mes > 6) {
            return LocalDate.of(hoy.getYear(), Month.JULY, 1);
        }
        // Si estamos en el primer semestre (enero a junio), el último semestre es el del año anterior (julio - diciembre).
        else {
            return LocalDate.of(hoy.getYear() - 1, Month.JULY, 1);  // Cambié el año a `hoy.getYear() - 1`
        }
    }
}
