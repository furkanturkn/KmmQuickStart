//
//  Colors.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

extension SwiftUI.Color {
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }

    private static let colors = Colors()
    
    static let darkGray = Color(hex: colors.DarkGray)
    static let mediumGray = Color(hex: colors.MediumGray)
    static let lightGray = Color(hex: colors.LightGray)
    static let textWhite = Color(hex: colors.TextWhite)
    static let primaryColor = Color(hex: colors.PrimaryColor)
    static let surfaceColor = Color(hex: colors.SurfaceColor)
    static let onSurfaceColor = Color(hex: colors.OnSurfaceColor)

    static let primary = Color(light: .primaryColor, dark: .primaryColor)
    static let background = Color(light: .white, dark: .white)
    static let onPrimary = Color(light: .white, dark: .white)
    static let surface = Color(light: .surfaceColor, dark: .surfaceColor)
    static let onBackground = Color(light: .black, dark: .black)
    static let onSurface = Color(light: .onSurfaceColor, dark: .onSurfaceColor)
}

private extension SwiftUI.Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(light)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}

