import { Component, OnInit } from '@angular/core';
import {ArtistService} from "../shared/artist.service";
import {Artist} from "../shared/artist.model";

@Component({
  selector: 'app-artists-update',
  templateUrl: './artists-update.component.html',
  styleUrls: ['./artists-update.component.css']
})
export class ArtistsUpdateComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit(): void {
  }

  updateArtist(id: string, name: string, establishmentYear: string): void {
    let artist: Artist = <Artist> {id: +id, name, establishmentYear: +establishmentYear};
    this.artistService.updateArtist(artist).subscribe(artist => {
      console.log("The updated artist: ", artist);
    });
  }
}
