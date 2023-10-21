package model.iphone;

import model.iphone.media.Image;
import model.iphone.media.Music;
import model.iphone.media.player.MusicPlayer;
import model.iphone.phone.Call;
import model.iphone.phone.Contact;
import model.iphone.phone.Phone;
import model.iphone.web.browser.WebBrowser;

import java.util.*;

public class Iphone implements MusicPlayer, Phone, WebBrowser {
    private List<Music> musicAlbum;
    private List<Image> photoAlbum;
    private List<Contact> contactList;
    private List<Call> simultaneousCalls;
    private Call currentCall;
    private Music playingMusic;

    public Iphone() {
        this.musicAlbum = new ArrayList<>();
        this.photoAlbum = new ArrayList<>();
        this.contactList = new ArrayList<>();
        this.simultaneousCalls = new ArrayList<>();
        this.currentCall = null;
        this.playingMusic = null;
    }


    @Override
    public Music findMusic(String musicName) {
        Music result = musicAlbum.stream().filter(music -> music.getName().equals(musicName)).findFirst().orElse(null);
        System.out.println("Música encontrada: " + result);
        return result;
    }

    @Override
    public void playMusic(Music music) {
        music.play();
        this.playingMusic = music;
    }

    @Override
    public void pauseMusic() {
        this.playingMusic.pause();
    }

    @Override
    public void terminateMusic() {
        this.playingMusic.terminate();
        this.playingMusic = null;
    }

    @Override
    public void skipForward() {
        int length = musicAlbum.size();
        if (length == 0) {
            System.out.println("Não há nenhuma música neste album!");
            return;
        }

        int index = musicAlbum.indexOf(playingMusic);
        int nextMusicIndex = (index + 1) % length;
        playMusic(musicAlbum.get(nextMusicIndex));
    }

    @Override
    public void skipBackward() {
        int length = musicAlbum.size();
        if (length == 0) {
            System.out.println("Não há nenhuma música neste album!");
            return;
        }

        int index = musicAlbum.indexOf(playingMusic);
        int prevMusicIndex = (index + length - 1) % length;
        playMusic(musicAlbum.get(prevMusicIndex));
    }

    @Override
    public void changeVolume(Integer newVolume) {
        System.out.println("Novo valor de volume: " + newVolume);
    }

    @Override
    public void changeRingtone(Music music) {
        System.out.println("Ringtone configurado: " + music);
    }

    @Override
    public void makeCall(String phoneNumber) {
        this.currentCall = new Call(phoneNumber);
        this.simultaneousCalls.add(currentCall);

        if (this.playingMusic != null) {
            this.playingMusic.pause();
        }
        System.out.println("Realizando chamada: " + currentCall);
    }

    @Override
    public void makeCall(Contact contact) {
        this.currentCall = new Call(contact);
        this.simultaneousCalls.add(currentCall);

        if (this.playingMusic != null) {
            this.playingMusic.pause();
        }
        System.out.println("Realizando chamada: " + currentCall);
    }

    @Override
    public void endCurrentCall() {
        System.out.println("Finalizando chamada: " + currentCall);
        this.simultaneousCalls.remove(currentCall);
        this.currentCall = null;

        if (!simultaneousCalls.isEmpty()) {
            this.currentCall = simultaneousCalls.get(0);
        }
        if (playingMusic != null && currentCall == null) {
            this.playingMusic.play();
        }
    }

    @Override
    public void acceptIncomingCall(Call call) {
        this.currentCall = call;
        this.simultaneousCalls.add(call);

        if (this.playingMusic != null) {
            this.playingMusic.pause();
        }
        System.out.println("Chamada atendida: " + call);
    }

    @Override
    public void rejectIncomingCall(Call call) {
        System.out.println("Chamada recusada: " + call);
    }

    @Override
    public void browseInternet(String keywordsSearch) {
        System.out.println("Pesquisa: " + keywordsSearch);
    }

    @Override
    public void openWebPage(String url) {
        System.out.println("Abrindo pagina de endereço: " + url);
    }

    @Override
    public void addToBookmarks(String url) {
        System.out.println("Endereço " + url + " foi adicionado aos favoritos");
    }

    public void addMusic(Music music) { this.musicAlbum.add(music); }

    public void addImage(Image image) { this.photoAlbum.add(image); }

    public void addContact(Contact contact) { this.contactList.add(contact); }

    public Call getCurrentCall() { return this.currentCall; }

    public Music getPlayingMusic() {
        return this.playingMusic;
    }

    public void changeCall() {
        int length = simultaneousCalls.size();
        if (length == 0) {
            System.out.println("Não há nenhuma chamada neste album!");
            return;
        }

        System.out.println("Troncando de chamada: ");
        currentCall.hold();
        int index = simultaneousCalls.indexOf(currentCall);
        int nextCallIndex = (index + 1) % length;
        this.currentCall = simultaneousCalls.get(nextCallIndex);

        System.out.println("Chamada atual: " + currentCall);
    }

    public void sendMessage(String phoneNumber, String message) {
        System.out.println("Enviando para número telefonico: " + phoneNumber + "; mensagem: " + message);
    }

    public void sendMessage(Contact contact, String message) {
        System.out.println("Enviando para contato: " + contact + "; mensagem: " + message);
    }

    public void mergeCalls() {
        if (simultaneousCalls.size() <= 1) {
            System.out.println("Não é possível realizar merge com " + simultaneousCalls.size() + "chamada(s)");
            return;
        }

        System.out.println("Realizando merge das chamadas: ");
        System.out.println("    Chamada atual: " + currentCall);

        List<Call> callsToMerge = new ArrayList<>(simultaneousCalls);
        callsToMerge.remove(currentCall);

        for (Call call : callsToMerge) {
            call.getParticipants().forEach(participant -> {
                currentCall.addParticipant(participant);
            });
        }

        System.out.println("    Chamada atual: " + currentCall);

        simultaneousCalls = new ArrayList<>();
        simultaneousCalls.add(currentCall);
    }

    public void splitCall(Call call) {
        Set<Contact> setParticipants = call.getParticipants();
        int totalParticipants = setParticipants.size();

        if (totalParticipants <= 1) {
            System.out.println("Não é possível realizar split com " + totalParticipants + "participantes(s)");
            return;
        }

        System.out.println("Realizando split dos participantes da chamada atual: ");
        System.out.println("    Chamada atual: " + currentCall);

        for (Contact participant : setParticipants) {
            System.out.println("    Isolando participante: " + participant);
            simultaneousCalls.add(new Call(participant));
        }
        simultaneousCalls.remove(currentCall);
        currentCall = simultaneousCalls.get(simultaneousCalls.size()-1);

        System.out.println("    Chamada atual: " + currentCall);
        System.out.println("    Chamadas Simultaneas: " + simultaneousCalls);
    }
}
