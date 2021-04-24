import { Component, OnInit } from '@angular/core';
import {ArtistService} from "../shared/artist.service";
import {Artist} from "../shared/artist.model";

@Component({
  selector: 'app-artists-list',
  templateUrl: './artists-list.component.html',
  styleUrls: ['./artists-list.component.css']
})
export class ArtistsListComponent implements OnInit {
  artists: Artist[] = [];

  constructor(private artistService: ArtistService) { }

  ngOnInit(): void {
    this.artistService.getArtists().subscribe(artists => {
      console.log(artists['artists']);
      this.artists = artists['artists'];
    });
  }

}
