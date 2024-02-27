//
//  StandardButton.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StandardButton: View {
    var onClick: () -> Void
    var text: String?
    var textColor: Color = .white
    var buttonColor: Color = .primary
    var enabled: Bool = true
    var content: (() -> AnyView)? = nil
    
    var body: some View {
        Button(action: onClick) {
            VStack {
                if let content = content {
                    content()
                }
                if let text = text {
                    Text(text)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .foregroundColor(textColor)
                        .background(buttonColor)
                        .cornerRadius(16)
                }
            }
        }
        .disabled(!enabled)
        
    }

}
