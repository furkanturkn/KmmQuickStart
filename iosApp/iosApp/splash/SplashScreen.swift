//
//  SplashScreen.swift
//  iosApp
//
//  Created by Furkan Turkan on 23.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct SplashScreen: View {
    
    @ObservedObject var viewModel: IosSplashViewModel
    
    private var sharedPref: KmmPreference
    
    @EnvironmentObject var router: Router
    
    init(
        sharedPref: KmmPreference
    ) {
        self.sharedPref = sharedPref
        
        self.viewModel = IosSplashViewModel(
            sharedPref: sharedPref
        )
    }
    
    
    var body: some View {
        ZStack {
            Color.primary
                .ignoresSafeArea()
            
            Image(resource: \.test)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .accessibilityLabel(Strings().get(
                    id: SharedRes.strings().app_logo_alt,
                    args: []
                ))
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .onReceive(viewModel.$state) { newState in
                if let route = newState.currentRoute {
                    switch route {
                    case Router.Destination.login.stringValue:
                        router.navigate(to: .login)
                    case Router.Destination.home.stringValue:
                        router.navigate(to: .home)
                    default:
                        break
                    }
                }
            }
    }
    
}
