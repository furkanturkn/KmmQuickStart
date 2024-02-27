//
//  IosSplashViewModel.swift
//  iosApp
//
//  Created by Furkan Turkan on 23.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

extension SplashScreen {
    @MainActor class IosSplashViewModel: ObservableObject {
     
        private let viewModel: SplashViewModel

      
        @Published var state: SplashContractState = SplashContractState(
            currentRoute: nil
        )
        private var handle: DisposableHandle?

        init(
            sharedPref: KmmPreference        
        ) {
            self.viewModel = SplashViewModel(
                sharedPref: sharedPref,
                coroutineScope: nil
            )
        }

        func onEvent(event: SplashContractEvent) {
            self.viewModel.onEvent(event: event)
        }

        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }

        func dispose() {
            handle?.dispose()
        }
         
    }
}

