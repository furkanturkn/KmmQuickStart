//
//  Router.swift
//  iosApp
//
//  Created by Furkan Turkan on 23.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

final class Router: ObservableObject {

    public enum Destination: Codable, Hashable {
        case login
        case home

        var stringValue: String {
            switch self {
            case .login:
                return Shared.Routes().LOGIN
            case .home:
                return Shared.Routes().HOME
            }
        }
    }

    @Published var navPath = NavigationPath()

    func navigate(to destination: Destination) {
        navPath.append(destination)
    }

    func navigateBack() {
        navPath.removeLast()
    }


    func popScreens(_ amount: Int) {
        navPath.removeLast(amount)
    }

    func navigateToRoot() {
        navPath.removeLast(navPath.count)
    }
}
