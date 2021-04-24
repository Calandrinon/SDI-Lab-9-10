import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Artist} from "./artist.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private artistsUrl = 'http://localhost:8080/api/artists';
  private singleArtistUrl = 'http://localhost:8080/api/artist';

  constructor(private httpClient: HttpClient) { }

  getArtists(): Observable<Artist[]> {
    return this.httpClient.get<Artist[]>(this.artistsUrl);
  }

  saveArtist(artist: Artist): Observable<Artist> {
    return this.httpClient.post<Artist>(this.singleArtistUrl, artist);
  }

  updateArtist(artist: Artist): Observable<Artist> {
    return this.httpClient.put<Artist>(this.singleArtistUrl + "/" + artist.id, artist);
  }

  removeArtist(artist: Artist): Observable<Artist> {
    return this.httpClient.delete<Artist>(this.singleArtistUrl + "/" + artist.id);
  }
}
