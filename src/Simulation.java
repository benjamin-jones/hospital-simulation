import java.util.Random;
class Simulation extends Thread
{
        public void run()
        {
                Random gen = new Random();
                long currTime = System.currentTimeMillis()/1000;
                double r;
                EventGenerator.generatePatientEvent();
                EventGenerator.generateSymptomEvent();
                while (Game.run == true)
                {
                        while (Game.pause == true)
                        {
                                EventProcessor.processEvents();
                                try {
                                        sleep(1);
                                } catch (Exception e)
                                {
                                        break;
                                }
                        }

                        while ( ((System.currentTimeMillis()/1000) - currTime) < 1)
                        {
                                continue;
                        }
                        r = gen.nextDouble();
                        if ((r > 0.0) && (r < 0.1))
                        {
                                EventGenerator.generatePatientEvent();
                        }
                        if ( (r > 0.1) && (r < 0.2))
                        {
                                EventGenerator.generateSymptomEvent();
                        }

                        EventGenerator.generateClockEvent();
                        EventProcessor.processEvents();
                        currTime = System.currentTimeMillis()/1000;
                }
        }
}