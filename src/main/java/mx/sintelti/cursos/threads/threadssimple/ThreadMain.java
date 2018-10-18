package mx.sintelti.cursos.threads.threadssimple;

import java.time.chrono.ThaiBuddhistChronology;

public class ThreadMain {
    public static void main(String[] args) {
        Tarea tarea = new Tarea ();
        System.out.println(Thread.currentThread().getName());
        for (int i =1; i<=100; i++) {
            new Thread(tarea, String.valueOf(i)).start();
        }
        System.out.println("Tareas Lanzadas!");
    }

}
