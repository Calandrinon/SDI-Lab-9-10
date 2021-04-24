import { Component, OnInit } from '@angular/core';
import {ArtistService} from "../shared/artist.service";
import {Artist} from "../shared/artist.model";

@Component({
  selector: 'app-artists-add',
  templateUrl: './artists-add.component.html',
  styleUrls: ['./artists-add.component.css']
})
export class ArtistsAddComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit(): void {
  }

  saveArtist(id: string, name: string, establishmentYear: string): void {
    const artist: Artist = <Artist>{id: +id, name, establishmentYear: +establishmentYear};
    this.artistService.saveArtist(artist).subscribe(artist => {
      console.log("The artist ", artist, " has been added.");
    });
  }
}
