def unicode_to_escape(char):
    codepoint = ord(char)
    if codepoint <= 0xFFFF:  # BMP characters
        return f'\\u{codepoint:04x}'
    else:  # Non-BMP characters (e.g., emoji)
        # Convert to surrogate pairs (used in Java/.properties files)
        high = ((codepoint - 0x10000) // 0x400) + 0xD800
        low = ((codepoint - 0x10000) % 0x400) + 0xDC00
        return f'\\u{high:04x}\\u{low:04x}'



inputFiles  = ['messages_en.txt', 'messages_es.txt']
outputFiles = ['messages_en.properties', 'messages_es.properties']

SRC_IN      = 'main/webapp/resources/unicode_i18n/'
SRC_OUT     = 'main/webapp/resources/i18n/'

for inFile, outFile in zip(inputFiles, outputFiles):
    # Read the input file with UTF-8 encoding
    with open(SRC_IN + inFile, 'r', encoding='utf-8') as f:
        content = f.read()

    # Convert Unicode characters to their escape sequences
    escaped_content = []
    for char in content:
        if ord(char) >= 128:  # Non-ASCII characters
            escaped_content.append(unicode_to_escape(char))
        else:  # ASCII characters (including newlines and tabs)
            escaped_content.append(char)

    # Combine the list into a single string
    escaped_content = ''.join(escaped_content)

    # Write the escaped content to the output file
    with open(SRC_OUT + outFile, 'w', encoding='ascii') as o:
        o.write(escaped_content)

print(f'Translated Unicode to escape sequences from {inputFiles} to {outputFiles}')