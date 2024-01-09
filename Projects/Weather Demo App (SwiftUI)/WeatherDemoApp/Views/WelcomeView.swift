//
//  WelcomeView.swift
//  WeatherDemoApp
//
//  Created by Simeon Rubin on 9/1/2024.
//

import SwiftUI
import CoreLocationUI

struct WelcomeView: View {
    @EnvironmentObject var locationManager:
    LocationManager
    
    var body: some View {
        VStack{
            VStack(spacing: 20){
                Text("Welcome to the Weather App").bold().font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                
                Text("Please share your current location to get the weather in your area").padding()
                
            }.multilineTextAlignment(.center).padding()
            
            LocationButton(.shareCurrentLocation){
                locationManager.requestLocation()
            }.cornerRadius(30)
                .symbolVariant(/*@START_MENU_TOKEN@*/.fill/*@END_MENU_TOKEN@*/)
                .foregroundColor(.white)
            
        }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
    }
}

#Preview {
    WelcomeView()
}

