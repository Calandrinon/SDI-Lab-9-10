import { Component, OnInit } from '@angular/core';
import {ArtistService} from "../shared/artist.service";
import {Record, RecordType} from "../../records/shared/record.model";
import {Artist} from "../shared/artist.model";

@Component({
  selector: 'app-artists-remove',
  templateUrl: './artists-remove.component.html',
  styleUrls: ['./artists-remove.component.css']
})
export class ArtistsRemoveComponent implements OnInit {

  constructor(private artistService: ArtistService) { }

  ngOnInit(): void {
  }

  removeArtist(id: string): void {
    const artist: Artist = <Artist>{id: +id};
    this.artistService.removeArtist(artist)
      .subscribe(artist => console.log("The removed artist: ", artist));
  }
}
