package ua.goit.bot.system;

class Cleaner extends Thread {

    @Override
    public void run() {
        while (true) {
            ResponseCache.Key key = ResponseCache.toDelete();
            if (key != null) {
                ResponseCache.removeResponse(ResponseCache.toDelete());
            }
        }
    }
}






